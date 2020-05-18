package client;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class JLabelRenderer extends JLabel implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
		//On écrit dans le label ce que contient la cellule
		setText((value != null) ? value.toString() : "");
		Font font = new Font("Bahnschrift", Font.LAYOUT_NO_LIMIT_CONTEXT, 12);
        setHorizontalAlignment(SwingConstants.CENTER);
		setFont(font);
		//On retourne notre label
		return this;
	}

}
