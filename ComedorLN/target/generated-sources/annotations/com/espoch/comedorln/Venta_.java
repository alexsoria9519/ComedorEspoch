package com.espoch.comedorln;

import com.espoch.comedorln.Costousuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-09-24T09:41:33")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> intidventa;
    public static volatile SingularAttribute<Venta, Date> dtfecha;
    public static volatile SingularAttribute<Venta, Boolean> blnreserva;
    public static volatile SingularAttribute<Venta, Integer> intcantidad;
    public static volatile SingularAttribute<Venta, Boolean> blnestado;
    public static volatile SingularAttribute<Venta, Costousuario> intidcostousuario;

}