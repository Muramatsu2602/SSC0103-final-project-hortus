import org.eclipse.swt.widgets.Composite;

public class Teste extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Teste(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
