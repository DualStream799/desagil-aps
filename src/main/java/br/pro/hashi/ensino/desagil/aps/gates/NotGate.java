package br.pro.hashi.ensino.desagil.aps.gates;

import br.pro.hashi.ensino.desagil.aps.gates.NandGate;
import br.pro.hashi.ensino.desagil.aps.model.Emitter;
import br.pro.hashi.ensino.desagil.aps.model.Gate;

public class NotGate extends Gate {
    private final NandGate nand;

    public NotGate() {
        super("NOT", 1);

        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return nand.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand.connect(0, emitter);
        nand.connect(1, emitter);
    }
}
