import React, { Component } from 'react';
import * as API from '../APIs/api';
import { Link } from 'react-router-dom';
import Popup from './popup';

class ProjectItem extends Component {
  constructor() {
    super();
    this.state = {
      showPopup: false, 
      isFreelancer: false
    };
  }

  componentDidMount(){
    console.log('compWilMount');
    if (this.props.user !== this.props.project.employer) {
      this.setState({
        isFreelancer: true
      });
    }
  }


  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup
    });
  }

  render() {
    console.log(this.props.project.id);
    let projectName = this.props.project.id + '-' + this.props.project.projectName;
    let linkToProject = '/projects/'+ projectName;
    let projectOwner = this.props.project.employer;
    let linkToOwner = '/profile/' + projectOwner;
    return (
      <div className="ProjectItem">
        <ul> 
            <li>
                <Link id="project-name" to={linkToProject} ><strong>{this.props.project.projectName}</strong> </Link> <br />
                <br />
                <p>  <i> <strong> Project Description: </strong> </i> <br/>
                {this.props.project.description} </p> <br />
                <p> <i> <strong> Skills Required: </strong></i> <br/>
                {this.props.project.skills} </p> <br/>
                <p> <i> <strong> Estimated Budget: </strong></i> <br/>
                $ {this.props.project.budget} </p>  <br />
                <p> <i> <strong> Employer:</strong> </i> <br />
                   <Link to={linkToOwner}> {this.props.project.employer} </Link> </p> 
                  <p> <i> <strong> Status: </strong></i> <br/>
                  {this.props.project.status} </p> <br/>
                  <p> <i> <strong> Average Bid: </strong></i> <br/>
                ${this.props.project.avgBid} </p> <br/>
                <p> <i> <strong> Number of bids yet: </strong></i> <br/>
                {this.props.project.totalBids} </p> <br/>
                  {
                    this.props.project.bidAmount ? 
                     <p> <i>  <strong> My Bid Amount: </strong> </i> <br/>
                     $ {this.props.project.bidAmount} </p> : null
                  }
                  <br/>
                <div> 
                  {
                    this.state.isFreelancer && 
                    <button className="bid-button" onClick = {this.togglePopup.bind(this)}> Bid Now </button>
                  }
                </div>   
               
                {this.state.showPopup ? 
                  <Popup
                    project={this.props.project.name}
                    owner = {this.props.project.employer}
                    closePopup={this.togglePopup.bind(this)}
                    user={this.props.user}
                    currentUser = {this.props.currentUser}
                  />
                  : null
                }
            </li>
        </ul>
      </div>
    );
  }
}

export default ProjectItem;

