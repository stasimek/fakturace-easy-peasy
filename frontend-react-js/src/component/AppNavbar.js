import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
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

class AppNavbar extends Component {
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
			<Navbar color="dark" dark expand="md">
				<NavbarBrand tag={Link} to="/">Fakturace easy-peasy</NavbarBrand>
				<NavbarToggler onClick={this.toggle} />
				<Collapse isOpen={this.isOpen} navbar>
					<Nav className="me-auto" navbar>
						<NavItem>
							<NavLink tag={Link} to="/invoices">{t('Invoices')}</NavLink>
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
			</Navbar>
		);
	}
}

export default withTranslation()(AppNavbar);
