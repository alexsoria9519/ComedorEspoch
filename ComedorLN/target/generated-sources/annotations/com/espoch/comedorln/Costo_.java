package com.espoch.comedorln;

import com.espoch.comedorln.Costousuario;
import com.espoch.comedorln.Tipomenu;
import com.espoch.comedorln.Tipousuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-16T20:16:37")
@StaticMetamodel(Costo.class)
public class Costo_ { 

    public static volatile SingularAttribute<Costo, Integer> intidcosto;
    public static volatile SingularAttribute<Costo, Date> dtfecha;
    public static volatile SingularAttribute<Costo, Tipousuario> intidtipousuario;
    public static volatile SingularAttribute<Costo, String> strdetalle;
    public static volatile SingularAttribute<Costo, Boolean> blnestado;
    public static volatile CollectionAttribute<Costo, Costousuario> costousuarioCollection;
    public static volatile SingularAttribute<Costo, Double> mnvalor;
    public static volatile SingularAttribute<Costo, Tipomenu> intidtipomenu;

}