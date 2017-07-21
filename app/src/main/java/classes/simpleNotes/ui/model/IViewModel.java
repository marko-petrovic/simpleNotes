package classes.simpleNotes.ui.model;

import java.io.Serializable;

/**
 * Interface for all View Models. <p>
 * Currently used only to ensure that all ViewModels are Serializable, as ViewModels are very anemic.
 * Potentially could be packed with more common features for ViewModels, like some Comparable<T> or so..
 */
public interface IViewModel extends Serializable {
}