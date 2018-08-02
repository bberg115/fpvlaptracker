#pragma once

#include <Arduino.h>
#include "subscriber.h"

namespace statemanagement {

    enum class state_enum {
        STARTUP,
        CALIBRATION,
        CALIBRATION_DONE,
        SCAN,
        RACE,
        RSSI,
        RESTORE_STATE,
        ERROR
    };

    class StateManager : public pubsub::Subscriber<state_enum> {
    public:
        StateManager() : _state(state_enum::STARTUP), _storeState(state_enum::STARTUP) {
        }

        String toString() {
            if (this->isStateCalibration()) {
                return "Calibration";
            } else if (this->isStateRace()) {
                return "Race";
            } else if (this->isStateError()) {
                return "Error";
            } else if (this->isStateScan()) {
                return "Scan";
            } else if (this->isStateRssi()) {
                return "RSSI";
            } else {
                return "Unknown";
            }
        }

        boolean isStateStartup() {
            return this->_state == state_enum::STARTUP;
        }

        boolean isStateCalibration() {
            return this->_state == state_enum::CALIBRATION;
        }

        boolean isStateCalibrationDone() {
            return this->_state == state_enum::CALIBRATION_DONE;
        }

        boolean isStateScan() {
            return this->_state == state_enum::SCAN;
        }

        boolean isStateError() {
            return this->_state == state_enum::ERROR;
        }

        boolean isStateRace() {
            return this->_state == state_enum::RACE;
        }

        boolean isStateRssi() {
            return this->_state == state_enum::RSSI;
        }

        state_enum getState() {
            return this->_state;
        }

        void setState(state_enum state) {
            if (state != this->_state) {
                this->_state = state;
            }
        }

        void storeState() {
            this->_storeState = this->_state;
        }
        
        void restoreState() {
            this->_state = this->_storeState;
        }

        void update(state_enum *state){
            if (*state == state_enum::RESTORE_STATE) {
                state = &this->_storeState;
            }
            this->_state = *state;
#ifdef DEBUG
            Serial.print("state change request to ");
            Serial.println(this->toString());
#endif
        }
        
    private:
        state_enum _state;
        state_enum _storeState;

    };

}