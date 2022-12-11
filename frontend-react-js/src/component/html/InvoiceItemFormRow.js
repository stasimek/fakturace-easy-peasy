import React, { Component } from 'react';
import { Col, Row } from 'reactstrap';

/**
 * Responsive form for invoice rows.
 */
export default class InvoiceItemFormRow extends Component {

	render() {
		return (
			<Row className="mx-0 my-1">
				<Col xl="1" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[0]}</Col>
				<Col xl="1" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[1]}</Col>
				<Col xl="5" lg="8" md="8" sm="12" xs="12" className="ps-0 pe-1">{this.props.children[2]}</Col>
				<Col xl="0" lg="4" md="4" sm="0"  xs="0"  className="d-xl-none d-lg-block d-md-block d-sm-none d-xs-none"></Col>
				<Col xl="1" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[3]}</Col>
				<Col xl="1" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[4]}</Col>
				<Col xl="1" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[5]}</Col>
				<Col xl="2" lg="2" md="2" sm="3"  xs="6"  className="ps-0 pe-1">{this.props.children[6]}</Col>
			</Row>
		);
	}
}
