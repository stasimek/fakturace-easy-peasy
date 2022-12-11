import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Link, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Options from '../component/html/Options';
import FormRow from '../component/html/FormRow';
import InvoiceItemFormRow from '../component/html/InvoiceItemFormRow';
import Cookies from 'js-cookie';
import withParams from '../util/withParams'

class ReceivedInvoice extends Component {

	constructor(props) {
		super(props);
		this.state = {
			item: {
				items: []
			}
		};
		this.user = {};
		this.subjects = {};
		this.enums = {};
		this.handleChange = this.handleChange.bind(this);
		this.handleAddInvoiceRow = this.handleAddInvoiceRow.bind(this);
		this.handleChangeInvoiceRow = this.handleChangeInvoiceRow.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	async componentDidMount() {
	}

	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		let item = this.state.item;
		item[name] = value;
		this.setState({item});
	}

	handleAddInvoiceRow() {
		let item = this.state.item;
		item['items'].push({}); // Add empty invoice row.
		this.setState({item});
	}

	handleChangeInvoiceRow(event) {
		const target = event.target;
		const value = target.value;
		const [name, index] = target.name.split(/[\[\]]/);
		let item = this.state.item;
		item['items'][index][name] = value;
		this.setState({item});
	}

	async handleSubmit(event) {
		event.preventDefault();
		const {item} = this.state;
		item.type = 'RECEIVED';

		await fetch('/api/invoice' + (item.id ? '/' + item.id : ''), {
			method: (item.id) ? 'PUT' : 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN'),
			},
			body: JSON.stringify(item),
		});
		this.props.history.push('/received-invoices');
	}

	render() {
		const {t} = this.props;
		const {item} = this.state;
		return "";
	}
}

export default withTranslation()(withParams(ReceivedInvoice));
