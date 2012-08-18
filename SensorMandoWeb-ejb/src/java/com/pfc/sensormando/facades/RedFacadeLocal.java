/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Red;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface RedFacadeLocal {

    boolean create(Red red);

    boolean edit(Red red);

    boolean remove(Red red);

    Red find(Object id);

    List<Red> findAll();

    List<Red> findRange(int[] range);

    List<Red> findByParameters(Integer id, Integer canal, Integer idRed, String ip, String nombre, Integer puerto);

    int count();
}
