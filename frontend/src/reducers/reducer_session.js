export default function (state = {}, action) {
  console.log(action.type);
  console.log(action.payload);
    switch (action.type) {
      case 'CHECK_SESSION_FULFILLED':
        return {
          isLoggedin: true,
          user: action.payload
        }
      case 'CHECK_SESSION_PENDING':
        return {
          isLoggedin: false,
          user: ""
        }
      default:
        return state;
    };
  }