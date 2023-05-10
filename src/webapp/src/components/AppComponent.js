import '../assets/css/App.css';
import { connect } from 'react-redux';
import { FormComponent } from './FormComponent';
import { NotifierToatsComponent } from "./NotifierToast";

const mapStateToProps = (state) => ({
});

export const AppComponent = connect(mapStateToProps, {})((props) => {
  return (
    <div className="App">
      <FormComponent />
      <NotifierToatsComponent />
    </div>
  );
});