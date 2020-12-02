import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

export const Brand = props => (
  <NavbarBrand tag={Link} to="/agent" className="brand-logo">
    <span className="brand-title">BlackWidowC2</span>
    <span className="navbar-version">{appConfig.VERSION}</span>
  </NavbarBrand>
);
