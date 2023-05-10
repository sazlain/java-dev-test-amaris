import React from 'react';
import './assets/css/index.css';
import 'bootstrap/dist/css/bootstrap.css';
import { AppComponent } from './components/AppComponent';
import { createRoot } from 'react-dom/client';
import { Provider } from 'react-redux';
import store from './store/configureStore';
import { BrowserRouter } from "react-router-dom";

const Root = () => (
  <Provider store={store}>
    <BrowserRouter>
      <AppComponent />
    </BrowserRouter>
  </Provider>
);


const container = document.getElementById('root');
const root = createRoot(container !== null ? container : null);
root.render(<Root />);