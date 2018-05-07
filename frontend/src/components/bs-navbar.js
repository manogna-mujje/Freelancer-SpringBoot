import React, { Component } from 'react';
import * as API from '../APIs/api';
import { Link, Redirect } from 'react-router-dom';

class NavBar extends Component {
    constructor(props){
        super(props);
        this.state = {
            toggle: false,
            profile: false,
            dashboard: false
        }
        this.postProject = this.postProject.bind(this);
    }

    handleClick(){
        API.logout().then((res) => {
           if(res.status === 200){
            this.props.history.push('/login');
           }
        })
    }

    postProject(){
        this.setState({
            toggle: true
        })
    }

    render() {
        let URL = "http://localhost:8080";
        let linkToProfile =  URL + "/profile/"+ this.props.user;
        let linkToDashboard = URL + "/dashboard";
        if(this.state.toggle){
            return <Redirect to="/post-project"/>
        }
        return (
            <div id= "my-NavBar">
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li className="nav-item active">
                        <a className="nav-link" href={linkToProfile}>My Profile {this.state.profile && <span className="sr-only">(current)</span> } </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href={linkToDashboard}>Dashboard {this.state.dashbord && <span className="sr-only">(current)</span> } </a>
                    </li>
                    </ul>
                    <button className="btn btn-outline-warning my-2 my-sm-0" type="submit" onClick={this.postProject}>Post a Project</button>
                </div>
                </nav>
            </div>
        );
    }
}

export default NavBar;
