import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
import { Button, Form, FormGroup, Input, Label, InputGroup, InputGroupText, Row, Col } from 'reactstrap';
import Options from '../component/html/Options';
import FormRow from '../component/html/FormRow';
import InvoiceItemFormRow from '../component/html/InvoiceItemFormRow';
import Cookies from 'js-cookie';
import withParams from '../util/withParams'
import clone from '../util/clone'
import removeFromArray from '../util/removeFromArray'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons'
import Api from '../service/Api'

class IssuedInvoice extends Component {

	emptyInvoiceRow = {quantity: 1, vatRate: 21};

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
		this.handleRemoveInvoiceRow = this.handleRemoveInvoiceRow.bind(this);
		this.handleChangeInvoiceRow = this.handleChangeInvoiceRow.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	async componentDidMount() {
		const isNew = this.props.params.id === 'new';
		const urls = [
			'/api/invoices/generate-new-number',
			'/api/user',
			'/api/subjects/customers',
			'/api/enum/currency',
			!isNew ? '/api/invoice/' + this.props.params.id : null
		];
		const thenFunction = ([newInvoiceNumber, user, subjects, currency, invoice]) => {
			this.user = user;
			this.subjects = subjects;
			this.enums.currency = currency;
			const emptyInvoiceRow = clone(this.emptyInvoiceRow);
			emptyInvoiceRow.unit = user.defaultUnit;
			emptyInvoiceRow.unitPrice = user.defaultUnitPrice;
			this.setState({
				item: invoice || {
					number: newInvoiceNumber,
					items: [
						emptyInvoiceRow
					]
				}
			});
		};
		Api.getMany(urls, thenFunction);
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
		const emptyInvoiceRow = clone(this.emptyInvoiceRow);
		emptyInvoiceRow.unit = this.user.defaultUnit;
		emptyInvoiceRow.unitPrice = this.user.defaultUnitPrice;
		// TODO: defaultUnitPrice for subject
		item['items'].push(emptyInvoiceRow);
		this.setState({item});
	}

	handleRemoveInvoiceRow(index) {
		let item = this.state.item;
		removeFromArray(item['items'], index);
		this.setState({item});
	}

	handleChangeInvoiceRow(event) {
		const target = event.target;
		const value = target.value;
		const [name, index] = target.name.split(/[[\]]/);
		let item = this.state.item;
		item['items'][index][name] = value;
		this.setState({item});
	}

	async handleSubmit(event) {
		event.preventDefault();
		const {item} = this.state;
		item.type = 'ISSUED';
		const {t} = this.props;
		await Api.call(
			(item.id) ? 'PUT' : 'POST',
			'/api/invoice' + (item.id ? '/' + item.id : ''),
			item,
			t('Invoice saved.')
		);
		this.props.history.push('/issued-invoices');
	}

	render() {
		const {t, i18n} = this.props;
		const {item} = this.state;

		function invoiceRowLabelClass(index) {
			return 'mt-2 text-truncate w-100' + (index > 0 ? ' d-xl-none' : '');
		}

		const divStyle = {width: '99%'};
		return (
			<div style={divStyle}>
				<h2>{item.id ? t('Edit issued invoice') : t('New invoice')}</h2>
				<Form onSubmit={this.handleSubmit}>
					<FormRow>
						<FormGroup>
							<Label for="number">{t('Invoice number')}</Label>
							<Input type="text" name="number" id="number"
								   value={item.number || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="issueDate">{t('Issue date')}</Label>
							<Input type="text" name="issueDate" id="issueDate"
								   value={item.issueDate || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="taxableSupplyDate">{t('Taxable supply date')}</Label>
							<Input type="text" name="taxableSupplyDate" id="taxableSupplyDate"
								   value={item.taxableSupplyDate || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup>
							<Label for="dueDate">{t('Due date')}</Label>
							<Input type="text" name="dueDate" id="dueDate"
								   value={item.dueDate || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="subject">{t('Customer')}</Label>
							<Input type="select" name="subject" id="subject"
								   value={item.subject || ''}
								   onChange={this.handleChange}>
								<Options list={this.subjects} key="id" value="companyName"/>
							</Input>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup>
							<Label for="bankName">{t('Bank name')}</Label>
							<Input type="text" name="bankName" id="bankName"
								   value={this.user.bankName || ''}
								   disabled/>
						</FormGroup>
						<FormGroup>
							<Label for="bankAcountNumber">{t('Bank account number')}</Label>
							<Input type="text" name="bankAcountNumber" id="bankAcountNumber"
								   value={this.user.bankAcountNumber || ''}
								   disabled/>
						</FormGroup>
						<FormGroup>
							<Label for="variableSymbol">{t('Variable symbol')}</Label>
							<Input type="text" name="variableSymbol" id="variableSymbol"
								   value={(item.number || '').replace(/[^0-9]/, '')}
								   disabled/>
						</FormGroup>
					</FormRow>
					<FormRow className="d-none">
						<FormGroup>
							<Label for="currency">{t('Currency')}</Label>
							<Input type="select" name="currency" id="currency"
								   value={item.currency || this.user.currency}
								   onChange={this.handleChange}>
								<Options map={this.enums.currency} nonEmpty/>
							</Input>
						</FormGroup>
						<FormGroup>
							<Label for="exchangeRate">{t('Exchange rate')}</Label>
							<Input type="text" name="exchangeRate" id="exchangeRate"
								   value={item.exchangeRate || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>
					<FormRow>
						<FormGroup md="3" sm="6">
							<Label for="orderNumber">{t('Order number')}</Label>
							<Input type="text" name="orderNumber" id="orderNumber"
								   value={item.orderNumber || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
						<FormGroup md="9" sm="6">
							<Label for="description">{t('Description')}</Label>
							<Input type="text" name="description" id="description"
								   value={item.description || ''}
								   onChange={this.handleChange}/>
						</FormGroup>
					</FormRow>

					<Row>
						<Col>
							<FormGroup>
								{item.items.map(
									(invoiceItem, index) =>
									<InvoiceItemFormRow>
										<div className="mb-0">
											<Label for={'quantity_' + index} className={invoiceRowLabelClass(index)}>{t('Quantity')}</Label>
											<Input type="text" name={'quantity[' + index + ']'} id={'quantity_' + index}
												   value={invoiceItem.quantity || ''}
												   onChange={this.handleChangeInvoiceRow}/>
										</div>
										<div className="mb-0">
											<Label for={'unit_' + index} className={invoiceRowLabelClass(index)}>{t('Unit')}</Label>
											<Input type="text" name={'unit[' + index + ']'} id={'unit_' + index}
												   value={invoiceItem.unit || ''}
												   onChange={this.handleChangeInvoiceRow}/>
										</div>
										<div className="mb-0">
											<Label for={'description_' + index} className={invoiceRowLabelClass(index)}>{t('Description')}</Label>
											<Input type="text" name={'description[' + index + ']'} id={'description_' + index}
												   value={invoiceItem.description || ''}
												   onChange={this.handleChangeInvoiceRow}/>
										</div>
										<div className="mb-0">
											<Label for={'unitPrice_' + index}
												   className={invoiceRowLabelClass(index)}
												   title={i18n.language === 'cs' ? t('Unit price') : ''}>
												{t('Unit price')}
											</Label>
											<InputGroup>
												<Input type="text" name={'unitPrice[' + index + ']'} id={'unitPrice_' + index}
													   value={invoiceItem.unitPrice || ''}
													   onChange={this.handleChangeInvoiceRow}/>
												<InputGroupText>Kč</InputGroupText>
											</InputGroup>
										</div>
										<div className="mb-0">
											<Label for={'price_' + index} className={invoiceRowLabelClass(index)}>{t('Price')}</Label>
											<InputGroup>
												<Input type="text" id={'price_' + index}
													   value={(invoiceItem.quantity * invoiceItem.unitPrice).toFixed(2) || 0}
													   disabled/>
												<InputGroupText>Kč</InputGroupText>
											</InputGroup>
										</div>
										<div className="mb-0">
											<Label for={'vatRate_' + index} className={invoiceRowLabelClass(index)}>{t('VAT')}</Label>
											<InputGroup>
												<Input type="text" name={'vatRate[' + index + ']'} id={'vatRate_' + index}
													   value={invoiceItem.vatRate}
													   onChange={this.handleChangeInvoiceRow}/>
												<InputGroupText>%</InputGroupText>
											</InputGroup>
										</div>
										<div className="mb-0 position-relative">
											<Label for={'priceVat_' + index} className={invoiceRowLabelClass(index)}>{t('Price with VAT')}</Label>
											<InputGroup className="pe-5">
												<Input type="text" id={'priceVat_' + index}
													   value={(invoiceItem.quantity * invoiceItem.unitPrice * (1 + invoiceItem.vatRate / 100)).toFixed(2) || 0}
													   disabled/>
												<InputGroupText>Kč</InputGroupText>
											</InputGroup>
											<Button color="danger" title={t('Remove row')}
													className="position-absolute end-0 bottom-0"
													onClick={() => this.handleRemoveInvoiceRow(index)}>
												<FontAwesomeIcon icon={faTrash} />
											</Button>
										</div>
									</InvoiceItemFormRow>
								)}
							</FormGroup>
						</Col>
					</Row>

					<FormGroup>
						<Button color="primary" onClick={this.handleAddInvoiceRow}>{t('Add row')}</Button>
					</FormGroup>

					<FormGroup>
						<Button color="primary" type="submit">{t('Save')}</Button>{' '}
						<Button color="secondary" tag={Link} to="/issued-invoices">{t('Cancel')}</Button>
					</FormGroup>
				</Form>
			</div>
		);
	}
}

export default withTranslation()(withParams(IssuedInvoice));
