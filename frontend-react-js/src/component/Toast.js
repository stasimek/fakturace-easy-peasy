import { Component } from 'react';
import { ToastContainer, Slide } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default class Toast extends Component {
	render() {
		return (
			<ToastContainer transition={Slide}
							position="top-right"
							autoClose={5000}
							hideProgressBar
							newestOnTop={false}
							closeOnClick
							pauseOnFocusLoss
							draggable={false}
							pauseOnHover
							theme="dark"
							className="toast-message"
			/>
		);
	}
}
