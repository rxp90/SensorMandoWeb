/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Mando;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface MandoFacadeLocal {

    boolean create(Mando mando);

    boolean edit(Mando mando);

    boolean remove(Mando mando);

    Mando find(Object id);

    List<Mando> findAll();

    List<Mando> findByParameters(Integer id, Integer direccion, String nombre, Integer idReceptor);

    List<Mando> findRange(int[] range);

    int count();
}
