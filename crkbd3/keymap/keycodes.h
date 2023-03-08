#pragma once

#include "features/custom_shift_keys.h"

enum layers {
    L_BASE,
    L_SYMBOLS,
    L_SHORTCUTS,
    L_NUMBERS,
    L_RGB,
};

#define SHORT_SPC LT(2, KC_SPC)

#define TMUX LWIN(KC_1)
#define BROWSER LWIN(KC_2)
#define INTELLIJ LWIN(KC_3)
#define VSCODE LWIN(KC_4)

#define DQUOTE KC_DOUBLE_QUOTE
#define MINUS KC_MINUS

#define BSLS_0 KC_BSLS
#define EQUAL_1 KC_EQUAL
#define LPRN_4 KC_LPRN
#define RPRN_5 KC_RPRN
#define LBRC_7 KC_LBRC
#define RBRC_8 KC_RBRC

const custom_shift_key_t custom_shift_keys[] = {
  {MINUS, KC_NO}, // Shift - is Nothing

  {BSLS_0, KC_0}, // Shift \ is 0
  {EQUAL_1, KC_1}, // Shift = is 1
  {LPRN_4, KC_4}, // Shift ( is 4
  {RPRN_5, KC_5},
  {LBRC_7, KC_7},
  {RBRC_8, KC_8},

  // Shifted numeric keys
  {KC_INT2, KC_2},
  {KC_INT3, KC_3},
  {KC_INT6, KC_6},
  {KC_INT9, KC_9},
};

uint8_t NUM_CUSTOM_SHIFT_KEYS =
    sizeof(custom_shift_keys) / sizeof(custom_shift_key_t);