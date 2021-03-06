package pp2.scrum.utils;

import java.util.Comparator;

import pp2.scrum.controller.UserStoryHelper;

/**
 * @author yoshknight
 *
 */
public enum UserStoryHelperComparator implements Comparator<UserStoryHelper> {
	NUMERO_SORT {
			@Override
		    public int compare(UserStoryHelper o1, UserStoryHelper o2) {
		        return Long.valueOf(o1.getId()).compareTo(o2.getId());
		    }
	
		    @Override
		    public String toString() {
		        return "Número";
		    }
		},
	TITULO_SORT {
		    @Override
		    public int compare(UserStoryHelper o1, UserStoryHelper o2) {
		        return o1.getTitulo().compareTo(o2.getTitulo());
		    }
	
		    @Override
		    public String toString() {
		        return "Título";
		    }
	    },
	ESTADO_SORT {
		    @Override
		    public int compare(UserStoryHelper o1, UserStoryHelper o2) {    
		        return o1.getEstado().compareTo(o2.getEstado());
		    }
		    @Override
		    public String toString() {
		        return "Estado";
		    }
	    }, 
	RESPONSABLE_SORT {
		    @Override
		    public int compare(UserStoryHelper o1, UserStoryHelper o2) {
		        return o1.getResponsable().compareTo(o2.getResponsable());
		    }
		    @Override
		    public String toString() {
		        return "Responsable";
		    }
	    };
	
	public static Comparator<UserStoryHelper> decending(final Comparator<UserStoryHelper> other) {
	    return new Comparator<UserStoryHelper>() {
	        public int compare(UserStoryHelper o1, UserStoryHelper o2) {
	            return -1 * other.compare(o1, o2);
	        }
	    };
	}
	
	public static Comparator<UserStoryHelper> getComparator(final UserStoryHelperComparator... multipleOptions) {
	    return new Comparator<UserStoryHelper>() {
	        public int compare(UserStoryHelper o1, UserStoryHelper o2) {
	            for (UserStoryHelperComparator option : multipleOptions) {
	                int result = option.compare(o1, o2);
	                if (result != 0) {
	                    return result;
	                }
	            }
	            return 0;
	        }
	    };
	}

}
