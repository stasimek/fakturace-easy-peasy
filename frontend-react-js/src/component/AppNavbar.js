import React, {Component} from 'react';
import {
	Collapse,
	Navbar,
	NavbarToggler,
	NavbarBrand,
	Nav,
	NavItem,
	NavLink,
	UncontrolledDropdown,
	DropdownToggle,
	DropdownMenu,
	DropdownItem,
} from 'reactstrap';
import {Link} from 'react-router-dom';
import Login from './Login';

export default class AppNavbar extends Component {
	constructor(props) {
		super(props);
		this.state = {isOpen: false};
		this.toggle = this.toggle.bind(this);
	}

	toggle() {
		this.setState({
			isOpen: !this.state.isOpen
		});
	}

	render() {
		return (
			<Navbar color="dark" dark expand="md">
				<NavbarBrand tag={Link} to="/">Fakturace easy-peasy</NavbarBrand>
				<NavbarToggler onClick={this.toggle} />
				<Collapse isOpen={this.isOpen} navbar>
					<Nav className="me-auto" navbar>
						<NavItem>
							<NavLink tag={Link} to="/invoices">Invoices</NavLink>
						</NavItem>
						<UncontrolledDropdown nav inNavbar>
							<DropdownToggle nav caret>
								Options
							</DropdownToggle>
							<DropdownMenu right>
							<DropdownItem>Option 1</DropdownItem>
							<DropdownItem>Option 2</DropdownItem>
							<DropdownItem divider />
							<DropdownItem>Reset</DropdownItem>
						</DropdownMenu>
						</UncontrolledDropdown>
					</Nav>
					<Login/>
				</Collapse>
			</Navbar>
		);
	}
}