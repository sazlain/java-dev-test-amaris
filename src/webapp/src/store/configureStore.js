import { createStore, combineReducers } from 'redux';
import { reducers } from '../reducers';

const rootReducer = combineReducers(
    { main: reducers.main }
);

const store = createStore(rootReducer);

export default store;