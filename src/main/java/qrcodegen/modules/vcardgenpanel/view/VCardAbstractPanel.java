/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qrcodegen.modules.vcardgenpanel.view;

import qrcodegen.modules.vcardgenpanel.PropertyProviderViews;

import javax.swing.*;

/**
 *
 * @author Stefan Ganzer
 */
public abstract class VCardAbstractPanel extends JPanel implements PropertyProviderViews {

	@Override
	public JPanel getJPanel() {
		return this;
	}
}
