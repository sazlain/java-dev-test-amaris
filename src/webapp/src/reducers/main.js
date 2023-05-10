import { types } from '../constants/types';
const initialState = {
    showAlert: { show: false }
};
const main = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_SHOW_ALERT:
            return {
                ...state,
                showAlert: action.payload
            };
        default:
            return state;
    }
}
export default main;