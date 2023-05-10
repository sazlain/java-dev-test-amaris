import { types } from '../constants/types';

export function setShowAlert(data) {
    return {
        type: types.SET_SHOW_ALERT,
        payload: data
    }
}