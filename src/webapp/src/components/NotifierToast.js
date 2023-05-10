
import { useEffect } from 'react';
import { connect } from 'react-redux';
import { setShowAlert } from '../actions/main';
import '../assets/css/App.css';
import {
    Card,
    CardBody,
    Row,
    Col,
    CardHeader
} from "reactstrap";

const NotifierToats = (props) => {

    useEffect(() => {
        timerClose();
    }, [props.showAlert.show])

    const closeToast = () => {
        props.setShowAlert({ show: false })
    }

    const timerClose = () => {
        setTimeout(function () {
            props.setShowAlert({ show: false })
        }, 5000);
    }

    return (
        <div xs={12} md={6} lg={3} className="toast-notification-card" style={{ display: props.showAlert.show ? 'block' : 'none' }}>
            <Card className={'alert-type-' + props.showAlert.className}>
                <CardBody className="toast-notifier-card-body">
                    <Row>
                        <Col className='col-10'>
                            {props.showAlert.message}
                        </Col>
                        <Col className='col-2'>
                            <span style={{ cursor: 'pointer' }} onClick={closeToast}>X</span>
                        </Col>
                    </Row>

                </CardBody>
            </Card>
        </div>
    )
}


const mapStateToProps = (state) => ({
    showAlert: state.main.showAlert
});

export const NotifierToatsComponent = connect(mapStateToProps, { setShowAlert })(NotifierToats);