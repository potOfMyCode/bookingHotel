package controller.command;

import model.dao.exception.DaoException;
import model.entity.Application;
import model.entity.User;
import model.service.ApplicationService;
import model.service.UserService;
import model.util.LogGen;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.util.LogMsg.EXCEPTION_WRITE_RESPONSE;

public class BookingCommand implements Command {
    private final String LOCAL_RB_BASE_NAME = "lang";
    private final String CUR_LANG = "curLang";
    private final String SESSION_USER = "sessionUser";
    private final String NAME_PARAM = "name";
    private final String SURNAME_PARAM = "surname";
    private final String DATEFROM_PARAM = "dateFrom";
    private final String DATETO_PARAM = "dateTo";
    private final String MESSAGE2 = "msg2";

    private static Logger log = LogGen.getInstance();
    private ApplicationService applicationService;

    public BookingCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(SESSION_USER)).getRole());

        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(CUR_LANG)));

        Timestamp date = new Timestamp(System.currentTimeMillis());
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String countPeople = request.getParameter("count");
        String countChildren = request.getParameter("kids");
        String typeOfNumer = request.getParameter("typeOfNumber");
        String note = request.getParameter("note");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        if (!applicationService.validate(name, surname, dateFrom, dateTo, countPeople, countChildren, typeOfNumer)) {
            setResponseStatus(400, resourceBundle.getString("invalid.fillAll"), response);
            return "";
        }

        if (applicationService.validateDate(dateFrom, dateTo)) {
            setResponseStatus(400, resourceBundle.getString("invalid.invalidDate"), response);
            return "";
        }

        int amountPeople = Integer.valueOf(countPeople);
        int amountChildren = Integer.valueOf(countChildren);
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        if (request.getSession().getAttribute(CUR_LANG).equals("uk")){
            format = new SimpleDateFormat("dd/MM/yyyy");
        }
        Date dateFirst = null;
        Date dateSecond = null;
        try {
            //java.util.Date d = df.parse(Date);
            dateFirst = format.parse(dateFrom);
            dateSecond= format.parse(dateTo);
        } catch (ParseException ex) {
            log.error("Error in parsing date in booking.jsp", ex);
        }

        Application application = new Application();
        application.setUserId(((User) request.getSession().getAttribute(SESSION_USER)).getId());
        application.setName(name);
        application.setSurname(surname);
        application.setAmountPeople(amountPeople);
        application.setAmounChildren(amountChildren);
        application.setTypeOfNumber(typeOfNumer);
        application.setNote(note);
        application.setDateFrom(dateFirst);
        application.setDateTo(dateSecond);
        application.setDateOfBooking(date);
        application.setStatus(false);

        try {
            applicationService.createApplication(application);
        } catch (DaoException e) {
            log.error("Cannot create this application", e);
        }

        request.setAttribute(MESSAGE2, resourceBundle.getString("register.success"));

        try {
            response.getWriter().write(
                    request.getScheme() + "://" +
                            request.getServerName() +
                            ":" + request.getServerPort() +
                            request.getContextPath() +
                            request.getServletPath() + "/operation_success" +
                            (request.getQueryString() == null ? "" : "?" + request.getQueryString())
            );
        } catch (IOException e) {
            log.error(EXCEPTION_WRITE_RESPONSE, e);
        }
        return "";
    }

    private void setResponseStatus(int status, String msg, HttpServletResponse response){
        response.setStatus(status);
        try {
            response.getWriter().write(msg);
        } catch (IOException e) {
            log.error(EXCEPTION_WRITE_RESPONSE, e);
        }
    }
}
/*
<html xmlns="http://www.w3.org/1999/xhtml" class="gr__ticket_uz_gov_ua"><head><title>iShop - Оплата</title> <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> <meta name="language" content="ru"> <meta name="audience" content="All"> <meta name="robots" content="index, follow"> <meta name="description" content=""> <meta name="keywords" content=""><link rel="shortcut icon" href="
    https://ticket.uz.gov.ua/i/root/img/dealer/54/favicon.ico?647
" type="image/x-icon">








    <!--[if IE]>
    <meta http-equiv="x-ua-compatible" content="IE=9; IE=11; IE=Edge">
    <![endif]-->



    <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link _gv_mcss="" rel="stylesheet" href="https://ticket.uz.gov.ua/i/gateway/dealer/54/css/static/common.css?647
" type="text/css"><link _gv_mcss="" rel="stylesheet" href="https://ticket.uz.gov.ua/i/gateway/dealer/54/css/controller/Payment.new.css?647
" type="text/css"><link _gv_mcss="" rel="stylesheet" href="https://ticket.uz.gov.ua/i/gateway/css/methods/Ishop.css?647
" type="text/css">
    <link rel="canonical" href="https://ticket.uz.gov.ua/lfqh/">
            <link rel="alternate" hreflang="en" href="https://ticket.uz.gov.ua/en/lfqh/">                        <link rel="alternate" hreflang="uk" href="https://ticket.uz.gov.ua/uk/lfqh/">            <script data-avast-pam="y" type="text/javascript" name="AVAST_PAM_submitInjector">(function _submitInjector() {
        var f = document.querySelectorAll("form")[0]; // eslint-disable-line no-undef
        if (!f._avast_submit) {
          f._avast_submit = f.submit;
        }
        try {
          Object.defineProperty(f, "submit", {
            get: function get() {
              return function (prev_submit) {
                prev_submit.call(this);

                if (this._avast_inside_submit) {
                  return;
                }
                this._avast_inside_submit = true;

                var evt = document.createEvent("CustomEvent");
                evt.initEvent("scriptsubmit", true, true); // bubbling & cancelable
                this.dispatchEvent(evt);

                delete this._avast_inside_submit;
              }.bind(this, this._avast_submit);
            },
            set: function set(submitFunc) {
              this._avast_submit = submitFunc;
            }
          });
        } catch (ex) {
          // ignored
        }
      })();</script><script data-avast-pam="y" type="text/javascript" name="AVAST_PAM_submitInjector">(function _submitInjector() {
        var f = document.querySelectorAll("form")[0]; // eslint-disable-line no-undef
        if (!f._avast_submit) {
          f._avast_submit = f.submit;
        }
        try {
          Object.defineProperty(f, "submit", {
            get: function get() {
              return function (prev_submit) {
                prev_submit.call(this);

                if (this._avast_inside_submit) {
                  return;
                }
                this._avast_inside_submit = true;

                var evt = document.createEvent("CustomEvent");
                evt.initEvent("scriptsubmit", true, true); // bubbling & cancelable
                this.dispatchEvent(evt);

                delete this._avast_inside_submit;
              }.bind(this, this._avast_submit);
            },
            set: function set(submitFunc) {
              this._avast_submit = submitFunc;
            }
          });
        } catch (ex) {
          // ignored
        }
      })();</script></head> <body class="gw" data-gr-c-s-loaded="true">

        <div class="cont">
            <div id="top">
                                    <a id="logo" href="http://booking.uz.gov.ua/" class="custom"><img src="https://ticket.uz.gov.ua/i/root/img/dealer/54/logo.png" alt="http://booking.uz.gov.ua/"></a>
                                <div class="langs">
                    <a class="lang ru active" href="https://ticket.uz.gov.ua/lfqh/">рус</a><a class="lang en" href="https://ticket.uz.gov.ua/en/lfqh/">eng</a><a class="lang uk" href="https://ticket.uz.gov.ua/uk/lfqh/">укр</a>                </div>
                <a id="version_switch" href=""></a>
                <div class="clr"></div>
            </div>
            <div id="main_content">
                <div id="breadcrumbs">

            <span>iShop</span>

</div>

                <div id="page_information" class="information" style="display: none;">
                    <noscript>
                        <div>
                            <span>JavaScript выключен</span>
<p>Данный сайт использует <b>JavaScript</b> для отображения информации. В конфигурации Вашего браузера <b>JavaScript</b> отключен.</p>
<p>Пожалуйста, зайдите в панель параметров Вашего браузера и включите поддержку <b>JavaScript</b> для корректной работы сайта.</p>
                        </div>
                    </noscript>
                </div>
                <div id="page_information-success" class="information success" style="display: none;">
                    <noscript>
                        <div>
                            <span>JavaScript выключен</span>
<p>Данный сайт использует <b>JavaScript</b> для отображения информации. В конфигурации Вашего браузера <b>JavaScript</b> отключен.</p>
<p>Пожалуйста, зайдите в панель параметров Вашего браузера и включите поддержку <b>JavaScript</b> для корректной работы сайта.</p>
                        </div>
                    </noscript>
                </div>
                <div id="page_content">

                            <div class="payment-form step-pay skip-img type-card">
                        <div class="order_data">
    <div>
        <span>ID заказа:</span>
        <span>1458171321</span>
    </div>
    <div class="cost">
        <span>Сумма:</span>
        <span>150,88 грн</span>
    </div>
    <div>
        <span>Торговец:</span>
        <span>АО "Укрзализныця"</span>
    </div>
    <div>
        <span>Описание</span>
        <span class="wide">Оплата билетов (000B4001-ABF0191A)</span>
    </div>
</div>

            <div class="service_confirm_text">
        <div class="types">
                    <div class="forms type-card">
    <form autocomplete="off" method="post" action="" name="pay_form" data-cid="lfqh" class="AVAST_PAM_loginform">
        <input type="hidden" name="step" value="pay">
        <input type="hidden" name="type_id" value="1">
        <input type="hidden" name="bank_id" value="">
        <input type="hidden" name="tkn" value="41bbcfbc451ab850b07b2664700cd247">

<input type="hidden" name="cvv2" value="">
<input type="hidden" name="cvv_map_used" value="">
<div class="card-form">
        <div class="card-list">
                <div class="card-block guest">
            <div class="card">
                <div class="title-block">Оплата картой<span class="amount-block"></span></div>
                <div class="num-block">
                    <span>Номер карточки</span><br>
                    <input name="cnum" type="tel" value="" placeholder="XXXX XXXX XXXX XXXX" maxlength="19" class="unknown">
                </div>
                <div class="dates-block">
                    <span>Срок действия</span><br>
                    <input name="expire_month" type="tel" maxlength="2" value=""> / <input type="tel" name="expire_year" maxlength="2" value="">
                </div>
                <div class="cvv-block">
                    <span>CVV2</span><a class="q" href="#" target="_">?</a><br>
                    <input id="input-cvv" class="cvv" name="cvv[new]" type="password" value="" placeholder="•••" autocomplete="off" maxlength="3" pattern="\d{3}" data-map="">
                    <div class="cvvkb">
                        <a href="#" class="erase" target="_"><b>x</b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                        <a href="#" target="_"><b style="background: url(&quot;/?w=0&amp;h=0&amp;fw=700&amp;fs=16px&amp;ff=roboto&amp;c[r]=0&amp;c[g]=0&amp;c[b]=238&amp;cvvkb=15654306159304629108303662939000&quot;) 0px center;"></b></a>
                    </div>
                </div>
            </div>
        </div>
            </div>
</div>


            <p class="total-amount">Сумма к оплате: <span>150,88 грн</span></p>




            <p class="continue">

                <div class="fine-button default"><input id="service_confirm_payment_button" type="submit" class="button" value="Оплатить"></div>
            </p>

    </form>
</div>
                            <div class="alternative">
                <p class="">Другие способы оплаты</p>
                    <div class="choose">
        <form autocomplete="off" name="type_select_form" action="" method="post">
            <input type="hidden" name="step" value="type">
            <input type="hidden" name="type_id" value="">
            <input type="hidden" name="bank_id" value="">



                                    <div class="types">





                        <div class="button">
                            <button id="type-9-gwf_-bank_" data-payment-type="9" type="button" title="Оплатить с кошелька MasterPass" onclick="pt.prepare('9', '', '');" class="type-9"></button>
                        </div>
                    </div>
                                    </form>

        <script type="text/javascript">
            var pt = new App.PaymentTypes(1, 1);
        </script>
    </div>

            </div>
            </div>
    <script type="text/javascript">
        var prepay = 0;
                    var bins = 15088;
            App.paymentCardForm(document.pay_form, prepay, bins);
            </script>
    </div>

        </div>

</div>
            </div>
        </div>
        <div id="bottom">
            <div class="partners">
                <img src="https://ticket.uz.gov.ua/i/root/img/partners/plategka.png" alt="Plategka.com"><img src="https://ticket.uz.gov.ua/i/root/img/partners/mc.png" alt="MasterCard"><img src="https://ticket.uz.gov.ua/i/root/img/partners/visa.png" alt="Visa"><img src="https://ticket.uz.gov.ua/i/root/img/partners/prostir.png" alt="Простір"><img src="https://ticket.uz.gov.ua/i/root/img/partners/pcidss.png" alt="PCIDSS">
            </div>
                            <!-- root: gateway -->

                                        <div class="contacts">
    <div class="list">
        <div class="title">Служба поддержки</div>
        <div class="phone"><a href="tel:+380445911988">+380 (44) 591-1988</a></div>
        <div class="skype"><a href="skype:live:uz_booking?chat">uz_booking</a></div>
        <div class="email"><a href="mailto:booking@uz.gov.ua">booking@uz.gov.ua</a></div>
    </div>

    <div class="visa-master">
        <a href="https://usa.visa.com/personal/security/vbv/index.jsp" class="vvisa" target="_blank"></a>
        <a href="http://www.mastercard.us" class="mcard-secure" target="_blank"></a>
        <a href="https://usa.visa.com/personal/security/vbv/index.jsp" class="visa" target="_blank"></a>
        <a href="http://www.mastercard.us" class="mcard" target="_blank"></a>
        <a href="http://prostir.gov.ua" class="prostir" target="_blank"></a>
    </div>
</div>

                    </div>


</body></html>
* */