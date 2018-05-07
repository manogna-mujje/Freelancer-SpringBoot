import React, {Component} from 'react';
import * as API from '../APIs/api';
import Menu from './menu';
import {checkSession} from '../actions/index';
import { connect } from 'react-redux';
import { bindActionCreators, compose } from 'redux';
import { bidsTabClick } from '../actions';
import { detailsTabClick } from '../actions';
import ProjectItem from './projectItem';
import BidItem from './bidItem';
import Payment from './payment';
import TransactionBills from './transactionBills';
import NavBar from './bs-navbar';
import { Link } from 'react-router-dom';


class Project extends Component {
    constructor(props){
        super(props);
        this.state = {
            user: '',
            projectName: '',
            bids: [],
            isAsc: false,
            description: "",
            budget: "",
            skills: "",
            employer: "",
            freelancer: "",
            salary: "",
            status: "",
            isEmployer: false,
            isFreelancer: false,
            isUser: true,
            fileURL: ''
        }
        this.handleUpload = this.handleUpload.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.makePayment = this.makePayment.bind(this);
        this.sort = this.sort.bind(this);
    }

    componentDidMount(){
        let resultArray = this.props.match.params.name.split("-");
        this.props.checkSession().then((res)=> {
            console.log('Check Session');
            this.setState({
                user: JSON.stringify(this.props.currentUser)
            })
        }).then(()=>{
            console.log('THIS USER: ')
            console.log(this.state.user);
        });

        this.props.bidsTabClick(resultArray[0]).then((data)=> {
            console.log(data);
            let bidArray = JSON.stringify(data.value);
            let bidObj = JSON.parse(bidArray);
            this.setState({
                bids:data.value
            });
            document.getElementById('Bids').style.display = "block";
            document.getElementById('Project-Details').style.display = "none";
        });

        this.props.detailsTabClick(resultArray[0]).then((data)=>{
            console.log(`This PROJECT: Details Tab Click`);
            console.log(data.value);
            this.setState({
                status: data.value.status,
                description: data.value.description,
                budget: data.value.budget,
                skills: data.value.skills,
                employer: data.value.employer
            });
            if(JSON.parse(this.props.details.list).postedProjects[0].hired){
                this.setState({
                    freelancer: JSON.parse(this.props.details.list).postedProjects[0].hired[0].freelancer,
                    salary: JSON.parse(this.props.details.list).postedProjects[0].hired[0].bidAmount
                })
            }

            if(this.state.user.username === this.state.employer) {
                this.setState({
                    isEmployer: true,
                    isUser: false
                });
            } else if(this.state.user.assignedProjects && this.props.match.params.name in this.state.user.assignedProjects) {
                this.setState({
                    isFreelancer: true,
                    isUser: false 
                });
            }
        });
    }

    sort(){
        this.setState({
            isAsc: !this.state.isAsc
        })
    }

    makePayment() {
        API.makePayment(this.props.match.params.name, this.state.freelancer, this.state.salary).then ((res) => {
            document.getElementById('payment-status').innerHTML = "Payment successfully made to Freelancer"
        })
    }

    handleUpload(){
        const data = new FormData();
        data.append('file', this.uploadInput.files[0]);
        data.append('filename', this.props.user);
        API.upload(data).then((response) => {
            response.json().then((body) => {
              this.setState({ fileURL: `http://localhost:3001/${body.file}` });
            });
          });
    }

    handleClick(event){
        console.log(event);
        if(event.target.id === 'bids-button') {
                document.getElementById('Bids').style.display = "block";
                document.getElementById('Project-Details').style.display = "none";
        } else if(event.target.id === 'project-details-button') {
            document.getElementById('Project-Details').style.display = "block";
            document.getElementById('Bids').style.display = "none";
                console.log(this.state.isEmployer);
                console.log(this.state.isFreelancer);
                console.log(this.state.isUser);

               
                
            // });
        }   
    }

    render(){
        console.log('PROJECT NAME:')
        let bidItems, bidsLength, bidAvg, num = 1;
        if(this.state.bids !== [] && typeof(this.state.bids) !== 'undefined'){

            // Sorting Bid Items
            let bidArray = this.state.bids;
            if(!this.state.isAsc){
                bidArray.sort(function(a,b){
                    return parseInt(b.bidAmount) - parseInt(a.bidAmount);
                })
            } else {
                bidArray.sort(function(a,b){
                    return parseInt(a.bidAmount) - parseInt(b.bidAmount);
                })
            }
            bidsLength = this.state.bids.length;
            let totalBidAmount = 0;
            console.log(typeof(this.state.bids));
            bidItems = bidArray.map((bid, index) => {
                console.log(bid);
                totalBidAmount = totalBidAmount + parseInt(bid.bidAmount);
                bidAvg = totalBidAmount/bidsLength;
                return (<li> <BidItem key ={index} project={this.props.match.params.name} bid={bid} isEmployer={this.state.isEmployer}/> </li>);
            });
        } else {
            bidsLength = 0;
            bidItems = 'No bids yet!';
        }

        let linkToEmployer;
        linkToEmployer = '/profile/'+ this.state.employer;

        let resultArray = this.props.match.params.name.split("-");
        
        return (
            <div className="project-details">
            <br/>
                <NavBar user={this.state.user.username} /> 
                <div id = "project-title"> 
                    {resultArray[1]}
                </div>
                <div id= "bid-summary"> 
                <ul>
                    <li> 
                       <span> Bids </span> <br/> {bidsLength}
                    </li>
                    <li> 
                    <span>  Avg Bid (USD) </span><br/> ${Math.ceil(bidAvg)}
                    </li>
                    <br />
                    <br />
                </ul>
                </div>
          
                <br />
                <div id="project-buttons"> 
                    <div className="tab">
                        <button className="tablinks"  id="bids-button" onClick={this.handleClick}>Bids</button>
                        <button className="tablinks" id="project-details-button" onClick={this.handleClick}>Project Details</button>
                        <div className="sort-buttons"> 
                            <button><div className="triangle-up" onClick = {this.sort}></div></button>
                            <button><div className="triangle-down" onClick = {this.sort} ></div></button>
                        </div>
                    </div>
                    <div id="Bids" className="tabcontent">
                        <ul> {bidItems} </ul>
                    </div>
                    <div id="Project-Details" className="tabcontent">
                        <br/>
                        <i> <strong> Project Description:</strong> </i>  <br/> {this.state.description} <br/> <br/>
                        <i> <strong>Estimated Budget: </strong> </i>  <br/> ${this.state.budget} <br/> <br/>
                        <i> <strong> Skills: </strong> </i>  <br/> {this.state.skills} <br/> <br/>
                        <i> <strong> Employer: </strong> </i> <br /> <Link to={linkToEmployer}> {this.state.employer} </Link> <br /> <br />
                        <i> <strong> Status: </strong> </i> <br/> {this.state.status} <br/>
                        <br/>
                        <div className="row">
                            <div className="col-sm-9">
                                { this.state.isFreelancer &&
                                <div id="freelancer-view">
                                <br/>
                                    <div id="file-upload"> 
                                    <i> Submit Files: </i>  <br/>
                                        <input ref={(ref) => {this.uploadInput = ref;}} type="file" />
                                        <a id="file" href={this.state.fileURL} />
                                    </div>
                                    <div id="comments-section">
                                    <br /> 
                                    <i> Comments: </i> <br />
                                        <input type="text" id = "text-box" name="text" /><br />
                                    </div>
                                    <button id="file-upload-button" onClick={this.handleUpload}> 
                                        Submit
                                    </button>
                                    </div>
                                }
                                { this.state.isEmployer &&
                                    <div id="employer-view">
                                        <button type="button" className="btn btn-primary" onClick={this.makePayment}>
                                                Make Payment
                                        </button>
                                        <p id="payment-status"> </p>                             
                                    </div>
                                 }
                            </div>
                            <div className="col-sm-3">
                            {
                                (this.state.isEmployer || this.state.isFreelancer) &&
                                    <div> 
                                        <TransactionBills user= {this.state.user} project={this.props.match.params.name} isEmployer={this.state.isEmployer} isFreelancer={this.state.isFreelancer} />
                                    </div>
                            }   
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state){
    return({
        bids: state.bids.list,
        details: state.details.list,
        currentUser: state.session.user
    });
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({ 
        bidsTabClick: bidsTabClick,
        detailsTabClick : detailsTabClick,
        checkSession: checkSession
    }, dispatch);
}


export default connect(mapStateToProps, mapDispatchToProps)(Project); 
