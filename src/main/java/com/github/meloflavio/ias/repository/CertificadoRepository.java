package com.github.meloflavio.ias.repository;

import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.util.Abstracts.AbstractRepository;
 import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;


@Repository
public interface CertificadoRepository extends AbstractRepository<Certificado, Integer> {

    @Query(value = "SELECT o FROM  Certificado o " +
            " WHERE o.responsavel=:pessoa AND o.competenciaRequerida=:curso and o.statusDocumento=:status")
    Certificado findOneByResponsavelByCurso(@Param("pessoa") PersonInterface pessoa, @Param("curso") Curso curso, @Param("status") StatusDocumento status);

    @Query(value = "SELECT o FROM  Certificado o " +
            " WHERE o.contractAddress =:hash  and o.statusDocumento = 1")
    Certificado findOneByHash(@Param("hash") String hash);

    @Query(value = "SELECT o FROM  Certificado o " +
            " WHERE o.contractAddress =:hash  and o.statusDocumento = 1")
    List<Certificado> findByHash(@Param("hash") String hash);
    List<Certificado> findByOrgAcreditada(OrganizationInterface organizacao);
    List<Certificado> findByProfissional(PersonInterface profissional);

}
