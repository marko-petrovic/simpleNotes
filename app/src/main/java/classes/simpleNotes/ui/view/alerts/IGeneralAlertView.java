package classes.simpleNotes.ui.view.alerts;

/**
 * Interface for alert views that will present various alert messages..
 */
public interface IGeneralAlertView extends IAlertView<GeneralAlert> {
    /**
     * @param listener that gets notified about user interaction from this view.
     */
    void setListener(Listener listener);

    /**
     * Listener interface that gets notified about user interaction from this view.
     */
    interface Listener {
        /**
         * Null-object pattern.
         */
        Listener NULL = new Listener() {
            @Override
            public void dismiss() {
                // Do nothing
            }

            @Override
            public void positiveButtonAction(GeneralAlert generalAlert) {
                // Do nothing
            }

            @Override
            public void negativeButtonAction(GeneralAlert generalAlert) {
                // Do nothing
            }

            @Override
            public void neutralButtonAction(GeneralAlert generalAlert) {
                // Do nothing
            }
        };

        /**
         * Called when user wants to dismiss information alert dialog.
         */
        void dismiss();

        /**
         * Called when user selects positive button from alert dialog.
         */
        void positiveButtonAction(GeneralAlert generalAlert);

        /**
         * Called when user selects negative button from alert dialog.
         */
        void negativeButtonAction(GeneralAlert generalAlert);

        /**
         * Called when user selects neutral button from alert dialog.
         */
        void neutralButtonAction(GeneralAlert generalAlert);
    }
}