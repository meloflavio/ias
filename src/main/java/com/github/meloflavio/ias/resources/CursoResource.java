package com.github.meloflavio.ias.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.meloflavio.ias.interfaces.DefinedTermInterface;
import com.github.meloflavio.ias.model.Curso;
import com.github.meloflavio.ias.repository.CursoRepository;
import com.github.meloflavio.ias.services.CursoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/curso")
public class CursoResource extends AbstractResource<Curso,Integer, CursoRepository, CursoService> {


    @Override
    protected String serializeSchema(Curso subject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode myData = objectMapper.createObjectNode();

        //2. Create a JSON-LD context
        ArrayNode context = objectMapper.createArrayNode();
        context.add("http://schema.org/");
        ObjectNode myContext=objectMapper.createObjectNode();
        myContext.put("id", "@id");
        myContext.put("type", "@type");
        context.add(myContext);

        //3. Combine context and data
        myData.set("@context",context);
        myData.put("type", subject.getType());
        myData.put("id", "/api/curso/"+subject.getId().toString());
        myData.put("name", subject.getNome());
        myData.put("description", Optional.ofNullable(subject.getDescricao()).orElse("no description"));
        myData.put("courseCode", Optional.ofNullable(subject.getCodigoCurso()).orElse(""));
        ArrayNode disciplinas = objectMapper.createArrayNode();
        for(DefinedTermInterface disciplina :  subject.getDisciplinas()){
            myData.put("@type", disciplina.getType());
            ObjectNode nodeDisciplina = objectMapper.createObjectNode();
            nodeDisciplina.put("name", disciplina.getNome());
            disciplinas.add(nodeDisciplina);
        }
        myData.set("competencyRequired",disciplinas);
        if(subject.getNivelEducacional() != null )
        myData.put("educationalLevel",subject.getNivelEducacional().getNome());

        //4. Print
        StringWriter w = new StringWriter();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(w, myData);
        return   w.toString();
    }
}
