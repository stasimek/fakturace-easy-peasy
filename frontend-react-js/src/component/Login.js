import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import $ from 'jquery';
import Cookies from 'js-cookie';
import { NavbarText } from 'reactstrap';

export default class Login extends Component {
	constructor(props) {
		super(props);
		this.handleLoad = this.handleLoad.bind(this);
	}

	componentWillMount() {
		this.handleLoad();
	}

	componentDidMount() {
		//window.addEventListener('load', this.handleLoad);
	}

	componentWillUnmount() {
		//window.removeEventListener('load', this.handleLoad)
	}

	handleLoad() {
		// Get logged user name
		$.get("/user", function (data) {
			$("#user").html(data.name);
			$(".unauthenticated").hide();
			$(".authenticated").show();
		});

		// CSRF
		$.ajaxSetup({
			beforeSend: function (xhr, settings) {
				if (settings.type == 'POST' || settings.type == 'PUT' || settings.type == 'DELETE') {
					if (!(/^http:.*/.test(settings.url) || /^https:.*/.test(settings.url))) {
						// Only send the token to relative URLs i.e. locally.
						xhr.setRequestHeader("X-XSRF-TOKEN", Cookies.get('XSRF-TOKEN'));
					}
				}
			}
		});

		// Show login error
		$.get("/error", function (data) {
			if (data) {
				$(".error").html(data);
			} else {
				$(".error").html('');
			}
		});
	}

	// Logout button
	logout(e) {
		e.preventDefault();
		$.post("/logout", function () {
			$("#user").html('');
			$(".unauthenticated").show();
			$(".authenticated").hide();
		});
	}

	render() {
		return (
			<div>
				<NavbarText className="text-danger error pe-3"></NavbarText>
				<NavbarText className="unauthenticated">
					<span className="pe-3">Login with <a href="/oauth2/authorization/github">GitHub</a></span>
					<span>Login with <a href="/oauth2/authorization/google">Google</a></span>
				</NavbarText>
				<NavbarText className="authenticated" style={{display:'none'}}>
					Logged in as: <span id="user" className="pe-3"></span>
					<button onClick={this.logout} className="btn btn-primary">Logout</button>
				</NavbarText>
			</div>
		);
	}
}
