export default function (state = {}, action) {
      switch (action.type) {
        case 'PROJECT_DETAILS_CLICK_FULFILLED':
        console.log('PROJECT_DETAILS_CLICK_FULFILLED');
        console.log(action.payload);
        return {
            list: action.payload
        }
        default:
          return state;
      };
    }