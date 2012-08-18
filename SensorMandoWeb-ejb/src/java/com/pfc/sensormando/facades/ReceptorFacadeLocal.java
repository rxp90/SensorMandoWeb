/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Receptor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface ReceptorFacadeLocal {

    boolean create(Receptor receptor);

    boolean edit(Receptor receptor);

    boolean remove(Receptor receptor);

    Receptor find(Object id);

    List<Receptor> findAll();

    List<Receptor> findByParameters(Integer id, Integer direccion, String nombre, Integer idRed);

    List<Receptor> findRange(int[] range);

    int count();
}
