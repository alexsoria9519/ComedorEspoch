package com.espoch.comedorln;

import com.espoch.comedorln.Planificacionmenu;
import com.espoch.comedorln.Tipomenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-09T10:15:31")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile CollectionAttribute<Menu, Planificacionmenu> planificacionmenuCollection;
    public static volatile SingularAttribute<Menu, String> strcaracteristicas;
    public static volatile SingularAttribute<Menu, Boolean> blnestado;
    public static volatile SingularAttribute<Menu, Tipomenu> intidtipomenu;
    public static volatile SingularAttribute<Menu, Integer> intidmenu;

}