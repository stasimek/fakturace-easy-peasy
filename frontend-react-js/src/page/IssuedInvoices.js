import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Button, ButtonGroup, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class IssuedInvoices extends Component {

	constructor(props) {
		super(props);
		this.state = {invoices: []};
		this.remove = this.remove.bind(this);
	}

	componentDidMount() {
		fetch('/api/invoices/issued')
				.then(response => response.json())
				.then(data => this.setState({invoices: data}));
	}

	async remove(id) {
		await fetch(`/api/invoice/${id}`, {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedInvoices = [...this.state.invoices].filter(i => i.id !== id);
			this.setState({invoices: updatedInvoices});
		});
	}

	render() {
		const {t} = this.props;
		const {invoices, isLoading} = this.state;

		if (isLoading) {
			return <p>Loading...</p>;
		}

		const invoiceList = invoices.map(invoice => {
			return (
				<tr key={invoice.id}>
					<td style={{whiteSpace: 'nowrap'}}>{invoice.number}</td>
					<td>
						<ButtonGroup>
							<Button size="sm" color="primary" tag={Link} to={"/invoice/" + invoice.id}>{t('Edit')}</Button>
							<Button size="sm" color="danger" onClick={() => this.remove(invoice.id)}>{t('Delete')}</Button>
						</ButtonGroup>
					</td>
				</tr>
			);
		});

		return (
			<div>
				<div className="float-right">
					<Button color="success" tag={Link} to="/issued-invoice/new">{t('Issue new invoice')}</Button>
				</div>
				<h2>{t('Issued invoices')}</h2>
				<Table className="mt-4">
					<thead>
						<tr>
							<th width="60%">Number</th>
							<th width="40%">{t('Actions')}</th>
						</tr>
					</thead>
					<tbody>
						{invoiceList}
					</tbody>
				</Table>
			</div>
		);
	}
}

export default withTranslation()(IssuedInvoices);
