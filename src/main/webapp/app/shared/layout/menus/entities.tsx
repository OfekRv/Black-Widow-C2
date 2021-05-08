import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/agent">
      Agent
    </MenuItem>
    <MenuItem icon="asterisk" to="/command">
      Command
    </MenuItem>
    <MenuItem icon="asterisk" to="/artifact">
      Artifact
    </MenuItem>
    <MenuItem icon="asterisk" to="/command-type">
      Command Type
    </MenuItem>
    <MenuItem icon="asterisk" to="/archived-async-artifact">
      Archived Async Artifact
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
