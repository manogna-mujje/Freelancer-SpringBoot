import React, { Component } from 'react';
import * as API from '../APIs/api';
import { Link } from 'react-router-dom';

class BidItem extends Component {

  handleClick() {
    API.hireFreelancer(this.props.project, this.props.bid.bidder, this.props.bid.bidAmount,this.props.bid.bidderEmail).then((res)=>{
      document.getElementById("bid-button").style.display = "none";
    })
  }

  render() {
      console.log(this.props.project);
      console.log(this.props.bid.bidderEmail);
      console.log(this.props.bid.freelancer);
      let bidder = this.props.bid.freelancer;
      let linkToBidder = '/profile/'+ bidder;
      let bidAmount = this.props.bid.bidAmount;
    return (
      <div id="bid-item">
          <div>
            <div > 
                <img id="pic" src={ `http://localhost:3001/files/${bidder}.jpg`} alt="img" />
            </div>
            <div id="bidder-details"> 
              <p> <i> Freelancer: </i> <br />
              <Link to={linkToBidder}> {this.props.bid.freelancer} </Link> </p> 
              <p> <i> BidAmount: </i> <br /> ${bidAmount} </p>
            </div>
          </div>
        <div  id="hire-button"> 
        {
          this.props.isEmployer && 
          <button id="bid-button" onClick = {this.handleClick.bind(this)}> Hire Now </button>
        }
        </div>
      </div>
    );
  }
}

export default BidItem;

