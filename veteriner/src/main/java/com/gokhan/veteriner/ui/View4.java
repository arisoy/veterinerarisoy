package com.gokhan.veteriner.ui;

import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = MainAppLayout.class)
public class View4 extends AbstractView {
  @Override
  String getViewName() {
    return getClass().getName();
  }
}