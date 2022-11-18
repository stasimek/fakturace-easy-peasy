import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

export default class Invoice extends Component {

	emptyItem = {
		number: ''
	};

	constructor(props) {
		super(props);
		this.state = {item: this.emptyItem};
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	async componentDidMount() {
		if (this.props.match.params.id !== 'new') {
			const invoice = await (await fetch(`/invoice/${this.props.match.params.id}`)).json();
			this.setState({item: invoice});
		}
	}

	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		let item = {...this.state.item};
		item[name] = value;
		this.setState({item});
	}

	async handleSubmit(event) {
		event.preventDefault();
		const {item} = this.state;

		await fetch('/invoice' + (item.id ? '/' + item.id : ''), {
			method: (item.id) ? 'PUT' : 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(item),
		});
		this.props.history.push('/invoices');
	}

	render() {
		const {item} = this.state;

		return (
			<div>
				<AppNavbar/>
				<Container>
					<h2>{item.id ? 'Edit Invoice' : 'Add Invoice'}</h2>
					<Form onSubmit={this.handleSubmit}>
						<FormGroup>
							<Label for="number">Number</Label>
							<Input type="text" name="number" id="number" value={item.number || ''}
								   onChange={this.handleChange} autoComplete="number"/>
						</FormGroup>
						<FormGroup>
							<Button color="primary" type="submit">Save</Button>{' '}
							<Button color="secondary" tag={Link} to="/invoices">Cancel</Button>
						</FormGroup>
					</Form>
				</Container>
			</div>
		);
	}
}
