export default function (state = {}, action) {
    switch (action.type) {
      case 'BIDS_CLICK_FULFILLED':
        console.log('BIDS_CLICK_FULFILLED');
        console.log(action.payload);
        if(action.payload.length !== 0){
          return {
            list: action.payload
          };
        }
        else {
          return {
            list: 'No Bids yet'
          };
        }
      default:
        return state;
    };
  }