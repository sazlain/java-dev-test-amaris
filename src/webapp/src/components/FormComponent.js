
import { useState } from 'react';
import {
    Col,
    Container,
    Form,
    FormGroup,
    Row,
    Card,
    CardHeader,
    CardBody,
    Input,
    Button
} from 'reactstrap';
import '../assets/css/App.css';
import { connect } from 'react-redux';
import DataTable from "react-data-table-component";
import Api from '../services/api';
import { setShowAlert } from '../actions/main';

const mapStateToProps = (state) => ({
});

export const FormComponent = connect(mapStateToProps, { setShowAlert })((props) => {

    const [dataTableinfo, setDataTableinfo] = useState([]);
    const [inputTextForm, setInputTextForm] = useState('');

    const paginationComponentOptions = {
        rowsPerPageText: 'Filas por página',
        rangeSeparatorText: 'de',
        selectAllRowsItem: true,
        selectAllRowsItemText: 'Todos',
    };

    const handleClickSearch = () => {

        if (inputTextForm === '') {
            Api.getEmployeesList()
                .then((resp) => {
                    setDataTableinfo(resp.data.data);
                    props.setShowAlert({ show: true, message: 'Information obtained successfully', className: "success", title: '' });
                })
                .catch((err) => {
                    console.log(err)
                    if (err.response.status === 502) {
                        props.setShowAlert({ show: true, message: err.response.data.message + '. Try again later', className: "danger", title: '' });
                    } else {
                        props.setShowAlert({ show: true, message: err.response.data.message, className: "danger", title: '' });
                    }
                    setDataTableinfo([]);
                });
        } else {
            const auxList = [];
            Api.getEmployeebyId(inputTextForm)
                .then((resp) => {
                    console.log(resp.data.data)
                    if (resp.data.data) {
                        auxList.push(resp.data.data);
                        props.setShowAlert({ show: true, message: 'Information obtained successfully', className: "success", title: '' });
                    } else {
                        props.setShowAlert({ show: true, message: 'Employee information not exist', className: "success", title: '' });
                    }
                    setDataTableinfo(auxList);

                })
                .catch((err) => {
                    if (err.response.status === 502) {
                        props.setShowAlert({ show: true, message: err.response.data.message + '. Try again later', className: "danger", title: '' });
                    } else {
                        props.setShowAlert({ show: true, message: err.response.data.message, className: "danger", title: '' });
                    }
                    setDataTableinfo([]);
                });
        }


    }

    const columns = [
        {
            name: 'Id',
            selector: row => row['id'],
            sortable: true,
            cell: (row) => {
                return <div>
                    {row.id}
                </div>
            }
        },
        {
            name: 'Name',
            selector: row => row['employeeName'],
            sortable: true,
            cell: (row) => {
                return <div>
                    {row.employeeName}
                </div>
            }
        },
        {
            name: 'Age',
            selector: row => row['employeeAge'],
            sortable: true,
            cell: (row) => {
                return <div>
                    {row.employeeAge}
                </div>
            }
        },
        {
            name: 'Month Salary',
            selector: row => row['employeeSalary'],
            sortable: true,
            right: true,
            cell: (row) => {
                return <div>
                    {row.employeeSalary}
                </div>
            }
        },
        {
            name: 'Anual Salary',
            selector: row => row['anualSalary'],
            sortable: true,
            right: true,
            cell: (row) => {
                return <div>
                    {row.anualSalary}
                </div>
            }
        },
        {
            name: 'Profile Image',
            selector: row => row['profileImage'],
            sortable: true,
            right: true,
            cell: (row) => {
                return <div>
                    {row.profileImage}
                </div>
            }
        }
    ];

    return (
        <Container fluid>
            <Row className='m-4'>
                <Col className="d-flex justify-content-center">
                    <Col xs={12} md={12} lg={6}>
                        <Card className="shadow" >
                            <CardHeader className="border-0">
                                Search Employees
                            </CardHeader>
                            <CardBody>
                                <Row>
                                    <Form>
                                        <FormGroup>
                                            <Col >
                                                <Input id="exampleEmail" placeholder="Enter the employee id" type="number" value={inputTextForm}
                                                    onChange={(e) => setInputTextForm(e.target.value)} />
                                            </Col>
                                        </FormGroup>
                                        <FormGroup>
                                            <Col >
                                                <Button color='primary' type='button' onClick={handleClickSearch}>Search</Button>
                                            </Col>
                                        </FormGroup>
                                    </Form>
                                </Row>
                            </CardBody>
                        </Card>
                    </Col>
                </Col>
            </Row>
            <Row className='m-4'>
                <Col className="d-flex justify-content-center">
                    <Col xs={12} md={12} lg={6}>
                        <Card className="shadow" >
                            <CardHeader className="border-0">
                                Employees List
                            </CardHeader>
                            <CardBody>
                                <DataTable
                                    columns={columns}
                                    data={dataTableinfo}
                                    pagination
                                    paginationComponentOptions={paginationComponentOptions} />
                            </CardBody>
                        </Card>
                    </Col>
                </Col>
            </Row>
        </Container>
    );
});