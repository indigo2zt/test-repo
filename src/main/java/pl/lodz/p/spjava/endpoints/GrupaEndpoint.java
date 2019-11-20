/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.endpoints;

import pl.lodz.p.spjava.ejb.facade.*;
import pl.lodz.p.spjava.ejb.intereceptor.LoggingInterceptor;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.exception.DbException;
import pl.lodz.p.spjava.web.utils.KontoUtils;

import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author java
 */

@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GrupaEndpoint extends AbstractEndpoint implements SessionSynchronization {
    
    @Inject
    private GrupaFacade grupaFacade;


    public void dodajUzytkownika(Grupa grupa) {
        grupaFacade.create(grupa);
    }

    public String znajdzPoLoginie(Konto konto) throws DbException.BadExecution {
        List<Grupa> listaGrup = grupaFacade.znajdzPoLoginie(konto);
        return listaGrup.stream().map(Grupa::getNazwagrupy).collect(Collectors.joining(","));
    }

    public List<Grupa> znajdzWszystkie(){
        return grupaFacade.findAll();
    }

    public void usunGrupe(Grupa grupa){
        grupaFacade.remove(grupa);
    }

    public void zapiszZmiany(Grupa grupa){
        grupaFacade.edit(grupa);
    }


    public List<Grupa> znajdzWszystkichPacjentow(){
        return grupaFacade.znajdzWszystkichPacjentow();
    }

    public void usunGrupeKonta(Konto konto){
        grupaFacade.usunByKonto(konto);
    }
}
