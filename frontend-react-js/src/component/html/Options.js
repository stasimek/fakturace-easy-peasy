import React, { Component, Fragment } from 'react';
import { withTranslation } from 'react-i18next';

/**
 * Render <option>s using parameter "map" or "list" as a source.
 *
 * Usage:
 *     <Options map={object}/>
 *     or
 *     <Options list={listOfObjects} itemId="idAttribute" itemLabel="labelAttribute"/>
 *
 * Optional attributes:
 *     nonEmpty - Don't render empty option.
 */
class Options extends Component {

	render() {
		if (
				this.props.map === undefined
				&& (!Array.isArray(this.props.list) || this.props.itemId === undefined || this.props.itemLabel === undefined)
		) {
			return;
		}

		const {t} = this.props;
		const emptyOption = this.props.nonEmpty === undefined ? <option>{t('--')}</option> : '';
		if (this.props.map !== undefined) {
			return (
				<Fragment>
					{emptyOption}
					{Object.entries(this.props.map).map(v => <option value={v[0]}>{v[1]}</option>)}
				</Fragment>
			)
		} else {
			return (
				<Fragment>
					{emptyOption}
					{this.props.list.map(
							v => <option value={v[this.props.itemId]}>{v[this.props.itemLabel]}</option>
					)}
				</Fragment>
			)
		}

	}
}

export default withTranslation()(Options);
