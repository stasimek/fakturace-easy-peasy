import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import {
	Collapse,
	Navbar as ReactstrapNavbar,
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

class Navbar extends Component {
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
		const {t} = this.props;
		return (
			<ReactstrapNavbar color="dark" dark expand="md">
				<NavbarBrand tag={Link} to="/">Fakturace easy-peasy</NavbarBrand>
				<NavbarToggler onClick={this.toggle} />
				<Collapse isOpen={this.isOpen} navbar>
					<Nav className="me-auto" navbar>
						<NavItem>
							<NavLink tag={Link} to="/issued-invoices">{t('Issued invoices')}</NavLink>
						</NavItem>
						<NavItem>
							<NavLink tag={Link} to="/received-invoices">{t('Received invoices')}</NavLink>
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
					<Nav navbar>
						<NavItem>
							<NavLink tag={Link} to="/setting">{t('Setting')}</NavLink>
						</NavItem>
					</Nav>
					<Login/>
				</Collapse>
			</ReactstrapNavbar>
		);
	}
}

export default withTranslation()(Navbar);
