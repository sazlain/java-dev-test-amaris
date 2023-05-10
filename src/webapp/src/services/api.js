import axios from "axios";
import config from "../config";

const Api = {
    getEmployeebyId: (data) => {

        const headers = {
            'Content-Type': 'application/json',
        }

        return axios({
            method: 'get',
            headers: headers,
            url: config.baseUrl + config.employeeByIdEndpoint + "?id=" + data,
            crossDomain: true,
        })

    },
    getEmployeesList: () => {

        const headers = {
            'Content-Type': 'application/json',
        }

        return axios({
            method: 'get',
            headers: headers,
            url: config.baseUrl + config.employeesListEndpoint,
            crossDomain: true,
        })

    }

}

export default Api;