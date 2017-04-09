package classes.simpleNotes.ui.model;

/**
 * Model class of general alert, carrying GeneralAlertType identifier and View Model. <br>
 * Used as AlertState for general alerts as being sent to general alert queue as queue item.
 */
public class GeneralAlert {
    /**
     * Type of displayed information alert message.
     */
    public enum GeneralAlertType {
        /**
         * Used to inform that note is not valid and thus can't be saved until corrected.
         */
        NOTE_NOT_VALID,
        /**
         * Default type that doesn't relate to any information that should be presented to a user. <br>
         * Passing this alert type into a state and to the View hides alert dialog view.
         */
        NOT_SPECIFIED
    }

    /**
     * Type of this alert.
     */
    public final GeneralAlertType generalAlertType;

    /**
     * View Model that might contain data that we want to present in alert.
     */
    public final IViewModel viewModel;

    /**
     * Default constructor.
     *
     * @param generalAlertType {@link GeneralAlertType} of this alert.
     * @param viewModel        data containing implementation of {@link IViewModel}.
     */
    public GeneralAlert(GeneralAlertType generalAlertType, IViewModel viewModel) {
        this.generalAlertType = generalAlertType;
        this.viewModel = viewModel;
    }

    private GeneralAlert(Builder builder) {
        this.generalAlertType = builder.generalAlertType;
        this.viewModel = builder.viewModel;
    }

    /**
     * Builder for {@link GeneralAlert}.
     */
    public static final class Builder {
        private GeneralAlertType generalAlertType = GeneralAlertType.NOT_SPECIFIED;
        private IViewModel viewModel = null;

        public Builder() {
        }

        public Builder(GeneralAlertType generalAlertType, IViewModel viewModel) {
            this.generalAlertType = generalAlertType;
            this.viewModel = viewModel;
        }

        public Builder(GeneralAlert copy) {
            this.generalAlertType = copy.generalAlertType;
            this.viewModel = copy.viewModel;
        }

        public Builder generalAlertType(GeneralAlertType value) {
            this.generalAlertType = value;
            return this;
        }

        public Builder viewModel(IViewModel value) {
            this.viewModel = value;
            return this;
        }

        /**
         * @return new instance of {@link GeneralAlert}
         */
        public GeneralAlert build() {
            return new GeneralAlert(this);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        GeneralAlert that = (GeneralAlert) object;

        if (generalAlertType != that.generalAlertType) return false;
        return viewModel != null ? viewModel.equals(that.viewModel) : that.viewModel == null;

    }

    @Override
    public int hashCode() {
        int result = generalAlertType != null ? generalAlertType.hashCode() : 0;
        result = 31 * result + (viewModel != null ? viewModel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GeneralAlert{" +
                "generalAlertType=" + generalAlertType +
                ", viewModel=" + viewModel +
                '}';
    }
}
