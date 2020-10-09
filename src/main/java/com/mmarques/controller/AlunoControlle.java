package com.mmarques.controller;

import com.mmarques.bo.Aluno;
import com.mmarques.bo.Retorno;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/alunos")
public class AlunoControlle {
    private static List<Aluno> alunos;
    
    static{
        alunos = new ArrayList<>();
        Aluno a = new Aluno();
        a.setNome("Marcos");
        a.setTurma("Turma A");
        a.setMedia(9.9);
        alunos.add(a);
        
        Aluno b = new Aluno();
        b.setNome("Simone");
        b.setTurma("Turma B");
        b.setMedia(9.5);
        alunos.add(b);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Aluno> getAlunos(){
        return alunos;
    }
    
    @Path("{indice}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Aluno getAluno(@PathParam("indice") int indice){
        return alunos.get(indice);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Retorno novo(Aluno a){
        alunos.add(a);
        Retorno r = new Retorno();
        r.setMensagem("Aluno Cadastrado com Sucesso");
        return r;
    }
    
    @Path("{indice}")
    @DELETE
    @Produces(MediaType.APPLICATION_XML)
    public Retorno remover(@PathParam("indice") int indice){
        alunos.remove(indice);
        Retorno r = new Retorno();
        r.setMensagem("Aluno Removido");
        return r;
    }
    
    @Path("{indice}")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Retorno atualizar(Aluno aluno, @PathParam("indice") int indice){
        alunos.set(indice,aluno);
        Retorno r = new Retorno();
        r.setMensagem("Aluno Atualizado");
        return r;
    }
}
