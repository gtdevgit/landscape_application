package com.example.mylandscapeapplication;

public enum NavigationState {
    HOME {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return (isLandscape) ? NavigationState.DETAIL : NavigationState.LIST;
        }
    },
    LIST {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return (! isLandscape);
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return (isLandscape) ? NavigationState.DETAIL : NavigationState.LIST;
        }
    },
    DETAIL {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            if (isLandscape)
                return (currentState != NavigationState.DETAIL);
            else
                return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return currentState;
        }
    },
    EDIT {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return currentState;
        }
    },
    ADD {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return currentState;
        }
    },
    MAP {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return currentState;
        }
    },
    SEARCH {
        @Override
        public boolean isEnable(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public boolean isVisible(boolean isLandscape, NavigationState currentState) {
            return true;
        }

        @Override
        public NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState) {
            return currentState;
        }
    };

    public abstract boolean isEnable(boolean isLandscape, NavigationState currentState);
    public abstract boolean isVisible(boolean isLandscape, NavigationState currentState);
    public abstract NavigationState redirectNavigation(boolean isLandscape, NavigationState currentState);
}
