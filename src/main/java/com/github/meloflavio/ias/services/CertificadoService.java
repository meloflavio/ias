package com.github.meloflavio.ias.services;

import com.github.meloflavio.ias.interfaces.OrganizationInterface;
import com.github.meloflavio.ias.interfaces.PersonInterface;
import com.github.meloflavio.ias.model.*;
import com.github.meloflavio.ias.repository.CertificadoRepository;
import com.github.meloflavio.ias.util.Abstracts.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificadoService extends AbstractService<Certificado,Integer, CertificadoRepository> {
    public Certificado findOneByResponsavelByCurso(PersonInterface pessoa, Curso curso){
        return repository.findOneByResponsavelByCurso(pessoa,  curso, StatusDocumento.VALIDO);
    }

    public Certificado findOneByHash(String hash){
        return repository.findOneByHash(hash);
    }

    public List<Certificado> findByHash(String hash){
        return repository.findByHash(hash);
    }

    public List<Certificado> findByOrgAcreditada(OrganizationInterface organizacao){ return repository.findByOrgAcreditada(organizacao); }
    public List<Certificado> findByProfissional(PersonInterface profissional){ return repository.findByProfissional(profissional); }

}
