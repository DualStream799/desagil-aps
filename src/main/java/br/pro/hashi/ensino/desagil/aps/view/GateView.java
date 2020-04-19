package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;
import br.pro.hashi.ensino.desagil.aps.view.FixedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener{
    private final Gate gate;

    private final JCheckBox input1;
    private final JCheckBox input2;
    private final JCheckBox output;
    private final Image image;
    private final Switch switch1;
    private final Switch switch2;

    public GateView(Gate gate) {

        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.
        super(245, 346);

        this.gate = gate;

        input1 = new JCheckBox("A");
        input2 = new JCheckBox("B");
        output = new JCheckBox("Q");
        switch1 = new Switch();
        switch2 = new Switch();

        output.setEnabled(false);

        // Não há mais a chamada de setLayout, pois ela agora
        // acontece no construtor da superclasse FixedPanel.

        // Como subclasse de FixedPanel, agora podemos definir a
        // posição e o tamanho de cada componente ao adicioná-la.
        if (this.gate.getInputSize() > 1) {
            add(input1, 10, 45, 75, 25);
            add(input2, 85, 45, 150, 25);
        } else {
            add(input1, 10, 45, 75, 25);
        }

        add(output, 85, 311, 120, 25);

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        input1.addActionListener(this::actionPerformed);
        input2.addActionListener(this::actionPerformed);

        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
        addMouseListener(this);

        update();
    }

    private void update() {
        output.setSelected(input1.isSelected());
        if (this.gate.getInputSize() == 2) {
            if (input1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            if (input2.isSelected()) {
                switch2.turnOn();
            } else {
                switch2.turnOff();
            }
            this.gate.connect(0, switch1);
            this.gate.connect(1, switch2);

            output.setSelected(this.gate.read());
        } else {
            if (input1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            this.gate.connect(0, switch1);

            output.setSelected(this.gate.read());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 80, 192, 80, this);
    }

}
