package Controllers;

import entities.Peliculasactores;
import Controllers.util.JsfUtil;
import Controllers.util.PaginationHelper;
import Facades.PeliculasactoresFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("peliculasactoresController")
@SessionScoped
public class PeliculasactoresController implements Serializable {

    private Peliculasactores current;
    private DataModel items = null;
    @EJB
    private Facades.PeliculasactoresFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PeliculasactoresController() {
    }

    public Peliculasactores getSelected() {
        if (current == null) {
            current = new Peliculasactores();
            current.setPeliculasactoresPK(new entities.PeliculasactoresPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PeliculasactoresFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Peliculasactores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Peliculasactores();
        current.setPeliculasactoresPK(new entities.PeliculasactoresPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getPeliculasactoresPK().setCodActor(current.getActores().getCodAutor());
            current.getPeliculasactoresPK().setCodPelicula(current.getPeliculas().getCodPelicula());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeliculasactoresCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Peliculasactores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPeliculasactoresPK().setCodActor(current.getActores().getCodAutor());
            current.getPeliculasactoresPK().setCodPelicula(current.getPeliculas().getCodPelicula());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeliculasactoresUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Peliculasactores) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeliculasactoresDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Peliculasactores getPeliculasactores(entities.PeliculasactoresPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Peliculasactores.class)
    public static class PeliculasactoresControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeliculasactoresController controller = (PeliculasactoresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peliculasactoresController");
            return controller.getPeliculasactores(getKey(value));
        }

        entities.PeliculasactoresPK getKey(String value) {
            entities.PeliculasactoresPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.PeliculasactoresPK();
            key.setCodPelicula(Integer.parseInt(values[0]));
            key.setCodActor(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entities.PeliculasactoresPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCodPelicula());
            sb.append(SEPARATOR);
            sb.append(value.getCodActor());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Peliculasactores) {
                Peliculasactores o = (Peliculasactores) object;
                return getStringKey(o.getPeliculasactoresPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Peliculasactores.class.getName());
            }
        }

    }

}
