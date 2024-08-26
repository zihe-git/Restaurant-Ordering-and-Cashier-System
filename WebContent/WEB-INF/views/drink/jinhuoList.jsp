<!-- 进货信息下一栏 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-12 col-md-12 " >
    <br/>
    <div class="table-responsive ">
        <div class="row">
            <div class="col-lg-3">
                <div class="input-group">
                    <span
                            class="input-group-addon ">供应商</span><select type="text" id="providerId" name="providerId"
                                                                         class="form-control providerSlecteDiv">

                </select>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="input-group">
                     <span
                             class="input-group-addon">商品名称</span><input type="text" id="productName" name="productName" class="form-control"/>
                </div>
            </div>
            <div class="col-lg-3">
                <button class="btn btn-default" id="serchDrinkBillBtn">查询</button>
            </div>
            <div class="tsx">
                <a class="btn btn-default showAddDrinkModal" > 添加 </a>
            </div>
        </div>
        <br/>
        <%-- div class=" col-md-3 col-sm-3 col-xs-3  "></div> --%>
        <div id="ajaxJinhuoListAppendDiv">


        </div>

    </div>
</div>
