/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Mando;
import com.pfc.sensormando.entity.Receptor;
import com.pfc.sensormando.entity.Red;
import com.pfc.sensormando.facades.MandoFacadeLocal;
import com.pfc.sensormando.facades.ReceptorFacadeLocal;
import com.pfc.sensormando.facades.RedFacadeLocal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Raul
 */
@ManagedBean
@RequestScoped
public class AdministrarRedBean {

    @EJB
    private MandoFacadeLocal mandoFacade;
    @EJB
    private ReceptorFacadeLocal receptorFacade;
    @EJB
    private RedFacadeLocal redFacade;
    private Mando mando;
    private Red red;
    //Identificador para la red del receptor. En value no me deja seleccionar la red y asociarla directamente.
    private Integer idRedSeleccionado;
    private Integer idReceptorSeleccionado;
    private Receptor receptor;
    private int exito;
    private final int CORRECTO = 1;
    private final int ERROR = -1;
    private static final Logger logger = Logger.getLogger(AdministrarRedBean.class.getName());
    private List<Red> redesBusqueda;
    private List<Receptor> receptoresBusqueda;
    private List<Mando> mandosBusqueda;

    /**
     * Creates a new instance of AdministrarRedBean
     */
    public AdministrarRedBean() {
        red = new Red();
        receptor = new Receptor();
        mando = new Mando();
    }

    public void crearMando() {
        if (mando != null) {
            Receptor receptorSeleccionado = receptorFacade.find(idReceptorSeleccionado);
            mando.setIdReceptor(receptorSeleccionado);

            Set<Mando> mandosReceptor = receptorSeleccionado.getMandoSet();
            mandosReceptor.add(mando);
            try {
                if (mandoFacade.create(mando)) {
                    exito = CORRECTO;
                } else {
                    exito = ERROR;
                }
            } catch (EJBException e) {
                exito = ERROR;
                logger.log(Level.SEVERE, "Error de persistencia {0}", e.getMessage());
            }

        }
    }

    public List<Red> getRedes() {
        return redFacade.findAll();
    }

    public List<Receptor> getReceptores() {
        return receptorFacade.findAll();
    }

    public void crearRed() {
        if (red != null) {
            try {
                if (redFacade.create(red)) {
                    exito = CORRECTO;
                } else {
                    exito = ERROR;
                }
            } catch (EJBException e) {
                exito = ERROR;
                logger.log(Level.SEVERE, "Error de persistencia {0}", e.getMessage());
            }
        }
    }

    public void crearReceptor() {
        if (receptor != null) {
            Red redSeleccionada = redFacade.find(idRedSeleccionado);
            receptor.setRed(redSeleccionada);
            Set<Receptor> receptoresRed = redSeleccionada.getReceptorSet();
            receptoresRed.add(receptor);
            try {
                if (receptorFacade.create(receptor)) {
                    exito = CORRECTO;
                } else {
                    exito = ERROR;
                }
            } catch (EJBException e) {
                exito = ERROR;
                logger.log(Level.SEVERE, "Error de persistencia {0}", e.getMessage());
            }
        }
    }

    public void buscarRed() {
        if (red != null) {
            redesBusqueda = redFacade.findByParameters(red.getId(), red.getCanal(), red.getIdRed(), red.getIp(), red.getNombre(), red.getPuerto());
        }
    }

    public void buscarReceptor() {
        if (receptor != null) {
            receptoresBusqueda = receptorFacade.findByParameters(receptor.getId(), receptor.getDireccion(), receptor.getNombre(), idRedSeleccionado);
        }
    }

    public void buscarMando() {
        if (mando != null) {
            receptoresBusqueda = receptorFacade.findByParameters(receptor.getId(), receptor.getDireccion(), receptor.getNombre(), idRedSeleccionado);
        }
    }

    public Mando getMando() {
        return mando;
    }

    public void setMando(Mando mando) {
        this.mando = mando;
    }

    public Red getRed() {
        return red;
    }

    public void setRed(Red red) {
        this.red = red;
    }

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public int getExito() {
        return exito;
    }

    public int getCORRECTO() {
        return CORRECTO;
    }

    public int getERROR() {
        return ERROR;
    }

    public Integer getIdRedSeleccionado() {
        return idRedSeleccionado;
    }

    public void setIdRedSeleccionado(Integer idRedSeleccionado) {
        this.idRedSeleccionado = idRedSeleccionado;
    }

    public Integer getIdReceptorSeleccionado() {
        return idReceptorSeleccionado;
    }

    public void setIdReceptorSeleccionado(Integer idReceptorSeleccionado) {
        this.idReceptorSeleccionado = idReceptorSeleccionado;
    }

    public List<Red> getRedesBusqueda() {
        return redesBusqueda;
    }

    public List<Receptor> getReceptoresBusqueda() {
        return receptoresBusqueda;
    }

    public List<Mando> getMandosBusqueda() {
        return mandosBusqueda;
    }
}
