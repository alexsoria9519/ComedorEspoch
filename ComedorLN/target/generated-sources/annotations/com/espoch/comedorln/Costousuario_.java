package com.espoch.comedorln;

import com.espoch.comedorln.Costo;
import com.espoch.comedorln.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-25T16:47:10")
@StaticMetamodel(Costousuario.class)
public class Costousuario_ { 

    public static volatile SingularAttribute<Costousuario, Costo> intidcosto;
    public static volatile SingularAttribute<Costousuario, String> strcedula;
    public static volatile CollectionAttribute<Costousuario, Venta> ventaCollection;
    public static volatile SingularAttribute<Costousuario, Integer> intidcostousuario;

}